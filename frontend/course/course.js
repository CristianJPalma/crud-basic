const api = 'http://localhost:8080';

// Elementos DOM
const mostrarRegistro = document.getElementById('mostrarRegister');
const agregarCurso = document.getElementById('agregarCurso');
const cerrarModal = document.getElementById('cerrarModal');
const lista = document.getElementById('lista');
const editCourseNameInput = document.getElementById('edit-course_name');
const editCourseIdInput = document.getElementById('edit-courseId');
const guardarCambiosBtn = document.getElementById('guardarCambios');
const eliminarCursoBtn = document.getElementById('eliminarCurso');

// Captcha widgets
let formCaptchaWidgetId = null;
let deleteCaptchaWidgetId = null;

// ID del curso seleccionado (global)
let selectedCourseId = null;

// Cargar captchas
function onCaptchaLoadCallback() {
    formCaptchaWidgetId = grecaptcha.render('formCaptcha', {
        'sitekey': '6LeADR4rAAAAAD0c9CIg4-8uRh6jMAnHzJTyzfzq'
    });
    deleteCaptchaWidgetId = grecaptcha.render('deleteCaptcha', {
        'sitekey': '6LeADR4rAAAAAD0c9CIg4-8uRh6jMAnHzJTyzfzq'
    });
}

// Mostrar modal agregar curso
document.addEventListener('DOMContentLoaded', () => {
    mostrarRegistro.addEventListener('click', () => {
        agregarCurso.classList.add('content-modal-activo');
        document.body.classList.add('modal-open');
    });

    cerrarModal.addEventListener('click', () => {
        agregarCurso.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
    });

    cargarcursos();
    setInterval(cargarcursos, 10000); // cada 10s para evitar tantos requests
});

// Agregar curso
document.getElementById('agregarCurso').querySelector('form').addEventListener('submit', async (event) => {
    event.preventDefault();
    const courseName = document.getElementById('course_name').value.trim();

    if (!courseName) {
        document.getElementById('course_name').classList.add('is-invalid');
        return;
    }
    document.getElementById('course_name').classList.remove('is-invalid');

    const recaptchaResponse = grecaptcha.getResponse(formCaptchaWidgetId);
    if (!recaptchaResponse) {
        alert("Completa el reCAPTCHA para continuar.");
        return;
    }

    const nuevoCurso = {
        course: {
            course_name: courseName,
            status: 1
        },
        recaptchaToken: recaptchaResponse
    };

    try {
        const res = await fetch(`${api}/api/v1/courses/`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevoCurso)
        });
        const data = await res.json();
        console.log('Curso creado:', data);

        document.getElementById('course_name').value = '';
        agregarCurso.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
        grecaptcha.reset(formCaptchaWidgetId);
        cargarcursos(); // recargar tabla
    } catch (error) {
        console.error('Error creando curso:', error);
    }
});

// Cargar cursos
async function cargarcursos() {
    try {
        const res = await fetch(`${api}/api/v1/courses/`);
        const cursos = await res.json();

        lista.innerHTML = ''; // limpiar tabla
        cursos.forEach(course => {
            const tr = document.createElement('tr');

            tr.innerHTML = `
                <td>${course.id_courses}</td>
                <td>${course.course_name}</td>
                <td>
                    <button class="btn btn-warning btn-sm me-2" data-id="${course.id_courses}" data-name="${course.course_name}" data-action="edit">Edit</button>
                    <button class="btn btn-danger btn-sm" data-id="${course.id_courses}" data-action="delete">Delete</button>
                </td>
            `;
            lista.appendChild(tr);
        });
    } catch (error) {
        console.error('Error cargando cursos:', error);
    }
}

// Manejar clicks en la tabla (edit y delete)
lista.addEventListener('click', (event) => {
    const action = event.target.dataset.action;
    const courseId = event.target.dataset.id;
    const courseName = event.target.dataset.name;

    if (!action) return;

    selectedCourseId = courseId;

    if (action === 'edit') {
        editCourseIdInput.value = courseId;
        editCourseNameInput.value = courseName;
        const modal = new bootstrap.Modal(document.getElementById('editarModal'));
        modal.show();
    } else if (action === 'delete') {
        document.getElementById('eliminaModalLabel').textContent = `Eliminar curso ${courseId}`;
        const modal = new bootstrap.Modal(document.getElementById('eliminaModal'));
        modal.show();
    }
});

// Guardar cambios
guardarCambiosBtn.addEventListener('click', async () => {
    const courseId = editCourseIdInput.value;
    const courseName = editCourseNameInput.value.trim();

    if (!courseName) {
        editCourseNameInput.classList.add('is-invalid');
        return;
    }
    editCourseNameInput.classList.remove('is-invalid');

    try {
        const res = await fetch(`${api}/api/v1/courses/${courseId}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ course_name: courseName })
        });

        if (res.ok) {
            console.log('Curso actualizado');
            cargarcursos();
            bootstrap.Modal.getInstance(document.getElementById('editarModal')).hide();
        } else {
            console.error('Error al actualizar el curso');
            alert('Error al actualizar el curso.');
        }
    } catch (error) {
        console.error('Error actualizando curso:', error);
    }
});

// Eliminar curso
eliminarCursoBtn.addEventListener('click', async (event) => {
    event.preventDefault();

    const recaptchaResponse = grecaptcha.getResponse(deleteCaptchaWidgetId);
    if (!recaptchaResponse) {
        alert("Completa el reCAPTCHA para continuar.");
        return;
    }

    if (!selectedCourseId) {
        alert("No se encontró el curso a eliminar.");
        return;
    }

    const courseWithCaptchaDTO = {
        course: { id_courses: selectedCourseId },
        recaptchaToken: recaptchaResponse
    };

    try {
        const res = await fetch(`${api}/api/v1/courses/`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(courseWithCaptchaDTO)
        });

        if (res.ok) {
            const data = await res.json();
            console.log('Curso eliminado:', data);
            alert(data.message);
            cargarcursos();
            bootstrap.Modal.getInstance(document.getElementById('eliminaModal')).hide();
        } else {
            const err = await res.json();
            console.error('Error eliminando curso:', err);
            alert(err.message || "Error eliminando curso.");
        }
    } catch (error) {
        console.error('Error en solicitud de eliminación:', error);
    } finally {
        grecaptcha.reset(deleteCaptchaWidgetId);
    }
});

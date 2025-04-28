const api = 'http://192.168.18.20:8080';

// Variables globales
const mostrarRegistro = document.getElementById('mostrarRegister');
const agregarCurso = document.getElementById('agregarCurso');
const cerrarModal = document.getElementById('cerrarModal');
const lista = document.getElementById('lista'); // Tabla de cursos
const editCourseNameInput = document.getElementById('edit-course_name'); // Input del nombre del curso
const editCourseIdInput = document.getElementById('edit-courseId'); // Input oculto para el ID del curso
const guardarCambiosBtn = document.getElementById('guardarCambios'); // Botón para guardar cambios
const eliminarCursoBtn = document.getElementById('eliminarCurso'); // Botón para eliminar curso

// Variables para los widgets de los reCAPTCHAs
let formCaptchaWidgetId = null;
let deleteCaptchaWidgetId = null;

// Función que se llama cuando los reCAPTCHAs se cargan
function onCaptchaLoadCallback() {
    formCaptchaWidgetId = grecaptcha.render('formCaptcha', {
        'sitekey': '6LeADR4rAAAAAD0c9CIg4-8uRh6jMAnHzJTyzfzq'
    });

    deleteCaptchaWidgetId = grecaptcha.render('deleteCaptcha', {
        'sitekey': '6LeADR4rAAAAAD0c9CIg4-8uRh6jMAnHzJTyzfzq'
    });
}

// Mostrar y cerrar el modal de agregar curso
document.addEventListener('DOMContentLoaded', () => {
    mostrarRegistro.addEventListener('click', () => {
        agregarCurso.classList.add('content-modal-activo');
        document.body.classList.add('modal-open');
    });

    cerrarModal.addEventListener('click', () => {
        agregarCurso.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
    });
});

// Validar y guardar un nuevo curso
document.getElementById('guardarCurso').addEventListener('click', function (event) {
    event.preventDefault();
    const courseNameInput = document.getElementById('course_name');
    let isValid = true;

    // Validar si el campo de entrada está vacío
    if (!courseNameInput.value.trim()) {
        courseNameInput.classList.add('is-invalid');
        isValid = false;
    } else {
        courseNameInput.classList.remove('is-invalid');
    }

    // Validar el reCAPTCHA del formulario de creación de curso
    const recaptchaResponse = grecaptcha.getResponse(formCaptchaWidgetId);
    if (recaptchaResponse.length === 0) {
        isValid = false;
    }

    // Si todo es válido, enviar el formulario
    if (isValid) {
        const nuevoCurso = {
            course: {
            course_name: courseNameInput.value.trim(),
            status: 1
            },
            recaptchaToken: recaptchaResponse
        };

        fetch(`${api}/api/v1/courses/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevoCurso)
        })
        .then(res => res.json())
        .then(data => {
            console.log('Curso creado:', data);
            courseNameInput.value = ''; // Limpiar el input
        })
        .catch(err => console.error(err));

        agregarCurso.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
        grecaptcha.reset(formCaptchaWidgetId); // Resetear el reCAPTCHA para el formulario
    }
});

// Cargar cursos disponibles
function cargarcursos() {
    fetch(`${api}/api/v1/courses/`)
        .then(respuesta => respuesta.json())
        .then(datos => {
            lista.innerHTML = '';
            datos.forEach(course => {
                const tr = document.createElement('tr');

                const tdId = document.createElement('td');
                tdId.textContent = course.id_courses;
                tr.appendChild(tdId);

                const tdName = document.createElement('td');
                tdName.textContent = course.course_name;
                tr.appendChild(tdName);

                const tdActions = document.createElement('td');
                tdActions.innerHTML = `
                    <button class="btn btn-warning btn-sm me-2" data-bs-toggle="modal"
                        data-bs-target="#editarModal" data-bs-id="${course.id_courses}">Edit</button>
                    <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal"
                        data-bs-target="#eliminaModal" data-bs-id="${course.id_courses}">Eliminate</button>
                `;
                tr.appendChild(tdActions);

                lista.appendChild(tr);
            });
        })
        .catch(error => console.error('Error al cargar los cursos:', error));
}

// Ejecutar la función cargarcursos cada segundo
setInterval(cargarcursos, 1000);

// Editar curso
lista.addEventListener('click', function (event) {
    if (event.target.classList.contains('btn-warning')) { // Detectar clic en el botón "Edit"
        const courseId = event.target.getAttribute('data-bs-id'); // Obtener el ID del curso
        const courseRow = event.target.closest('tr'); // Obtener la fila del curso
        const courseName = courseRow.querySelector('td:nth-child(2)').textContent; // Obtener el nombre del curso

        // Asignar valores al modal
        editCourseIdInput.value = courseId;
        editCourseNameInput.value = courseName;
    }
});

// Guardar cambios en un curso
guardarCambiosBtn.addEventListener('click', function () {
    const courseId = editCourseIdInput.value; // Obtener el ID del curso
    const courseName = editCourseNameInput.value.trim(); // Obtener el nuevo nombre del curso

    // Validar que el nombre del curso no esté vacío
    if (!courseName) {
        editCourseNameInput.classList.add('is-invalid');
        return;
    } else {
        editCourseNameInput.classList.remove('is-invalid');
    }

    const updatedCourse = { course_name: courseName };

    fetch(`${api}/api/v1/courses/${courseId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedCourse)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error al actualizar el curso');
        }
    })
    .then(data => {
        console.log('Curso actualizado:', data);
        const modal = bootstrap.Modal.getInstance(document.getElementById('editarModal'));
        modal.hide();
    })
    .catch(error => {
        console.error('Error al actualizar el curso:', error);
        alert('Hubo un problema al actualizar el curso');
    });
});

// Eliminar curso
eliminarCursoBtn.addEventListener('click', function (event) {
    event.preventDefault();

    const recaptchaResponse = grecaptcha.getResponse(deleteCaptchaWidgetId);
    if (recaptchaResponse.length === 0) {
        return;
    }

    const id = document.querySelector('[data-bs-target="#eliminaModal"]').dataset.bsId;

    fetch(`${api}/api/v1/courses/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            alert('Curso eliminado correctamente');
        } else {
            alert('Hubo un error al eliminar el curso');
        }
    })
    .catch(error => {
        console.error('Error al eliminar el curso:', error);
        alert('Hubo un problema al eliminar el curso');
    });

    const modal = bootstrap.Modal.getInstance(document.getElementById('eliminaModal'));
    modal.hide();
    grecaptcha.reset(deleteCaptchaWidgetId);
});

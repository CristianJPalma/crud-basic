const api = 'http://172.30.2.129:8080';
const mostrarRegistro = document.getElementById('mostrarRegister');
const agregarCurso = document.getElementById('agregarCurso');
const cerrarModal = document.getElementById('cerrarModal');
document.addEventListener('DOMContentLoaded', () => {
  
    mostrarRegistro.addEventListener('click', () => {
        // Mostrar el modal
        agregarCurso.classList.add('content-modal-activo');
        document.body.classList.add('modal-open');
    });
    cerrarModal.addEventListener('click', () => {
        agregarCurso.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
    });

});

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

// Validar y guardar curso
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
            course_name: courseNameInput.value.trim(),
            status: 1
        };

        // Petición POST para crear un nuevo curso
        fetch(api + '/api/v1/courses/', {
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
        grecaptcha.reset(formCaptchaWidgetId);  // Resetear el reCAPTCHA para el formulario
    }
});

// Consulta de cursos disponibles
document.addEventListener('DOMContentLoaded', function () {
    const lista = document.getElementById('lista');

    function cargarcursos() {
        fetch(api + '/api/v1/courses/')
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

                    const tdStatus = document.createElement('td');
                    tdStatus.textContent = course.status;
                    tr.appendChild(tdStatus);

                    const tdActions = document.createElement('td');
                    tdActions.innerHTML = `
                        <a href="" class="btn btn-warning btn-sm me-2">Edit</a>
                        <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal"
                            data-bs-target="#eliminaModal" data-bs-id="${course.id_courses}">Eliminate</button>
                    `;
                    tr.appendChild(tdActions);

                    lista.appendChild(tr);
                });
            })
            .catch(error => console.error('Error al cargar los cursos:', error));
    }

    // Ejecuta la función cargarcursos cada segundo
    setInterval(cargarcursos, 1000);
});

// Eliminar curso
document.getElementById('eliminarCurso').addEventListener('click', function (event) {
    event.preventDefault();  // Prevenir el comportamiento por defecto

    // Validar el reCAPTCHA para la eliminación
    const recaptchaResponse = grecaptcha.getResponse(deleteCaptchaWidgetId);
    if (recaptchaResponse.length === 0) {
        return;
    }

    // Obtener el ID del curso a eliminar
    let id = document.querySelector('[data-bs-target="#eliminaModal"]').dataset.bsId;

    // Realizar la solicitud DELETE para eliminar el curso
    fetch(api + '/api/v1/courses/' + id, {
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

    // Cerrar el modal de eliminación
    const modal = bootstrap.Modal.getInstance(document.getElementById('eliminaModal'));
    modal.hide();

    // Resetear el reCAPTCHA de eliminación
    grecaptcha.reset(deleteCaptchaWidgetId);
});

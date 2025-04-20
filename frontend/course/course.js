const api = 'http://localhost:8080';

// Validar y guardar curso
document.getElementById('guardarCurso').addEventListener('click', function () {
    const courseNameInput = document.getElementById('course_name');
    const captchaContainer = document.getElementById('captcha-container');
    const captchaError = document.getElementById('captcha-error');
    const recaptchaResponse = grecaptcha.getResponse();

    let isValid = true;

    // Validar si el campo de entrada está vacío
    if (!courseNameInput.value.trim()) {
        courseNameInput.classList.add('is-invalid');
        isValid = false;
    } else {
        courseNameInput.classList.remove('is-invalid');
    }

    // Validar si el CAPTCHA está resuelto
    if (!recaptchaResponse) {
        captchaError.classList.remove('d-none');
        captchaContainer.classList.add('border', 'border-danger', 'rounded');
        isValid = false;
    } else {
        captchaError.classList.add('d-none');
        captchaContainer.classList.remove('border', 'border-danger', 'rounded');
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
            grecaptcha.reset(); // Reiniciar el CAPTCHA
        })
        .catch(err => console.error(err));
    }
});

// Consulta de cursos disponibles
document.addEventListener('DOMContentLoaded', function () {
    const lista = document.getElementById('lista');
    let idEliminar; // Variable para almacenar el ID del curso a eliminar

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
                        <a href="" class="btn btn-warning btn-sm me-2">Editar</a>
                        <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal"
                            data-bs-target="#eliminaModal" data-bs-id="${course.id_courses}">Eliminar</button>
                    `;
                    tr.appendChild(tdActions);

                    lista.appendChild(tr);
                });

                // Agregar evento click a los botones "Eliminar"
                const botonesEliminar = document.querySelectorAll('[data-bs-id]');
                botonesEliminar.forEach(boton => {
                    boton.addEventListener('click', function (event) {
                        event.preventDefault(); // Evita que el botón recargue la página
                        idEliminar = this.getAttribute('data-bs-id'); // Captura el ID del curso
                        console.log('ID del curso a eliminar:', idEliminar);
                    });
                });
            })
            .catch(error => console.error('Error al cargar los cursos:', error));
    }

    // Ejecuta la función cargarcursos cada segundo
    setInterval(cargarcursos, 1000);
});

function setupModal() {
    const botonAgregar = document.getElementById('mostrarRegister');
    const modalElement = document.getElementById('modalCurso');

    if (typeof bootstrap !== 'undefined') {
        const modalInstance = new bootstrap.Modal(modalElement);

        botonAgregar.addEventListener('click', function () {
            modalInstance.show();
        });
    } else {
        console.error("Bootstrap no está disponible. Asegúrate de haber incluido Bootstrap JS.");
    }
}

document.addEventListener('DOMContentLoaded', function () {
    setupModal();
});
document.getElementById("contenido").addEventListener("submit", function (e) {
  const captchaResponse = grecaptcha.getResponse();

  const captchaError = document.getElementById("captcha-error");

  if (!captchaResponse) {
    e.preventDefault();
    captchaError.classList.remove("d-none");
    document.querySelector('.g-recaptcha').classList.add('is-invalid');
    return;
  } else {
    captchaError.classList.add("d-none");
    document.querySelector('.g-recaptcha').classList.remove('is-invalid');
  }

  // Continuar: aquí podrías hacer un fetch POST si estás usando API
});

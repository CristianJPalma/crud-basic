const api = 'http://172.30.4.228:8080';
const mostrarRegistro = document.getElementById('mostrarRegister')


mostrarRegistro.addEventListener('click',()=>{
    const agregarCurso = document.getElementById('agregar-user')
    agregarCurso.classList.add('activo')
    document.body.classList.add('modal-open')

})

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
    }
});

// Consulta de cursos disponibles
document.addEventListener('DOMContentLoaded', function () {
    const lista = document.getElementById('lista');
    let idEliminar;

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


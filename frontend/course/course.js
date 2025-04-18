const api = 'http://localhost:8080';

let guardarCurso = document.getElementById('guardarCurso');

guardarCurso.addEventListener('click', function(event) {
    
    event.preventDefault();

    let course_name = document.getElementById('course_name').value;
    
    let nuevoCurso = {
        course_name: course_name,
        status: 1
    };

    //Peticion POST para crear un nuevo curso
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
    })
    .catch(err => console.error(err));
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
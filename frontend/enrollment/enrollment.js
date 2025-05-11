const api = 'http://localhost:8080';

const lista = document.getElementById('lista');
const mostrarRegistro = document.getElementById('mostrarRegister');
const agregarCurso = document.getElementById('agregarEnrollment');
document.addEventListener('DOMContentLoaded', () => {
    mostrarRegistro.addEventListener('click', () => {
        agregarCurso.classList.add('content-modal-activo');
        document.body.classList.add('modal-open');
    });

    cerrarModal.addEventListener('click', () => {
        agregarCurso.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
    });
    cargarEnrollments();
    setInterval(cargarEnrollments, 10000);
});

async function cargardatos(){
    const lResponse = await fetch(`${api}/api/v1/learners/`);
    const learner = await lResponse.json();
            const lOpciones = document.getElementById('learner');
            learner.forEach(learner => {
                const option = document.createElement('option');
                option.value = learner.id_learner;
                option.textContent = `${learner.first_name} ${learner.last_name}`;
                lOpciones.appendChild(option);
            });
    const cResponse = await fetch(`${api}/api/v1/courses/`);
            const courses = await cResponse.json();
            const cOpciones = document.getElementById('course');
            courses.forEach(course => {
                const option = document.createElement('option');
                option.value = course.id_courses;
                option.textContent = course.course_name;
                cOpciones.appendChild(option);
            });
}cargardatos(); 

async function cargarEnrollments() {
    try {
        const res = await fetch(`${api}/api/v1/enrollments/`);
        const idEnrollment = await res.json();
        lista.innerHTML = '';
        idEnrollment.forEach(enrollment => {
             const tr = document.createElement('tr');

             tr.innerHTML = `
                <td>${enrollment.id_enrollment}</td>
                <td>${enrollment.id_learner.first_name}</td>
                <td>${enrollment.id_learner.last_name}</td>
                <td>${enrollment.id_course.course_name}</td>
                <td>${enrollment.enrollment_date}</td>
                <td>
                    <button class="btn btn-warning btn-sm me-2" data-id="${enrollment.id_enrollment}" data-name="${enrollment.id_learner.first_name}" data-action="edit">Edit</button>
                    <button class="btn btn-danger btn-sm" data-id="${enrollment.id_enrollment}" data-action="delete">Delete</button>
                </td>
            `;
             lista.appendChild(tr);
         });
    } catch (error) {
        console.error('Error cargando cursos:', error);
    }
}

// Registrar nuevo enrollment
document.getElementById('agregarEnrollment').querySelector('form').addEventListener('submit', async (event) => {
    event.preventDefault();

    const learner = document.getElementById('learner').value;
    const course = document.getElementById('course').value;
    const learnerId = Number(learner)
    const courseId = Number(course)


    // Obtener fecha actual en formato YYYY-MM-DD
    const today = new Date();
    const enrollmentDate = today.toISOString().split('T')[0];

    const nuevoEnrollment = {
        id_learner: { id_learner: learnerId },
        id_course: { id_courses: courseId },
        enrollment_date: enrollmentDate,
        status: 1
    };

    try {
        const res = await fetch(`${api}/api/v1/enrollments/`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevoEnrollment)
        });

        if (res.ok) {
            document.getElementById('agregarEnrollment').classList.remove('content-modal-activo');
            document.body.classList.remove('modal-open');
            event.target.reset();
            cargarEnrollments();
        } else {
            const errorMsg = await res.text();
            alert('Error al registrar el enrollment: ' + errorMsg);
        }
    } catch (error) {
        console.error('Error registrando enrollment:', error);
    }
});
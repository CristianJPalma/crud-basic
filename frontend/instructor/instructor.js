const api = 'http://192.168.18.20:8080';

// Elementos DOM
const mostrarRegistro = document.getElementById('mostrarRegister');
const agregarInstructor = document.getElementById('agregarInstructor');
const cerrarModal = document.getElementById('cerrarModal');
const lista = document.getElementById('lista');
const editFirstNameInput = document.getElementById('edit-first_name');
const editLastNameInput = document.getElementById('edit-last_name');
const editInstructorIdInput = document.getElementById('edit-instructorId');
const guardarCambiosBtn = document.getElementById('guardarCambios');
const eliminarInstructorBtn = document.getElementById('eliminarInstructor');

// Captcha widgets
let formCaptchaWidgetId = null;
let deleteCaptchaWidgetId = null;

// ID del instructor seleccionado (global)
let selectedInstructorId = null;

// Cargar captchas
function onCaptchaLoadCallback() {
    formCaptchaWidgetId = grecaptcha.render('formCaptcha', {
        'sitekey': '6LeADR4rAAAAAD0c9CIg4-8uRh6jMAnHzJTyzfzq'
    });
    deleteCaptchaWidgetId = grecaptcha.render('deleteCaptcha', {
        'sitekey': '6LeADR4rAAAAAD0c9CIg4-8uRh6jMAnHzJTyzfzq'
    });
}

// Mostrar modal agregar instructor
document.addEventListener('DOMContentLoaded', () => {
    mostrarRegistro.addEventListener('click', () => {
        agregarInstructor.classList.add('content-modal-activo');
        document.body.classList.add('modal-open');
    });

    cerrarModal.addEventListener('click', () => {
        agregarInstructor.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
    });

    cargarInstructores();
    setInterval(cargarInstructores, 10000); //
});

// Agregar instructor
document.getElementById('guardarinstructor').addEventListener('click', async (event) => {
    event.preventDefault();
    const firstName = document.getElementById('first_name').value.trim();
    const lastName = document.getElementById('last_name').value.trim();

    if (!firstName || !lastName) {
        document.getElementById('first_name').classList.add('is-invalid');
        document.getElementById('last_name').classList.add('is-invalid');
        return;
    }

    const recaptchaResponse = grecaptcha.getResponse(formCaptchaWidgetId);
    if (!recaptchaResponse) {
        alert("Complete the reCAPTCHA to continue.");
        return;
    }

    const nuevoInstructor = {
        instructor: {
            first_name: firstName,
            last_name: lastName,
            status: 1
        },
        recaptchaToken: recaptchaResponse
    };

    try {
        const res = await fetch(`${api}/api/v1/instructors/`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevoInstructor)
        });
        const data = await res.json();
        console.log('Instructor created:', data);

        document.getElementById('first_name').value = '';
        document.getElementById('last_name').value = '';
        agregarInstructor.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
        grecaptcha.reset(formCaptchaWidgetId);
        cargarInstructores(); // recargar tabla
    } catch (error) {
        console.error('Error creating instructor:', error);
    }
});

// Cargar instructores
async function cargarInstructores() {
    try {
        const res = await fetch(`${api}/api/v1/instructors/`);
        const instructores = await res.json();

        lista.innerHTML = ''; // limpiar tabla
        instructores.forEach(instructor => {
            const tr = document.createElement('tr');

            tr.innerHTML = `
                <td>${instructor.id_instructor}</td>
                <td>${instructor.first_name}</td>
                <td>${instructor.last_name}</td>
                <td>
                    <button class="btn btn-warning btn-sm me-2" data-id="${instructor.id_instructor}" data-firstname="${instructor.first_name}" data-lastname="${instructor.last_name}" data-action="edit">Edit</button>
                    <button class="btn btn-danger btn-sm" data-id="${instructor.id_instructor}" data-action="delete">Delete</button>
                </td>
            `;
            lista.appendChild(tr);
        });
    } catch (error) {
        console.error('Error loading instructors:', error);
    }
}

// Manejar clicks en la tabla (edit y delete)
lista.addEventListener('click', (event) => {
    const action = event.target.dataset.action;
    const instructorId = event.target.dataset.id;
    const firstName = event.target.dataset.firstname;
    const lastName = event.target.dataset.lastname;

    if (!action) return;

    selectedInstructorId = instructorId;

    if (action === 'edit') {
        editInstructorIdInput.value = instructorId;
        editFirstNameInput.value = firstName;
        editLastNameInput.value = lastName;
        const modal = new bootstrap.Modal(document.getElementById('editarModal'));
        modal.show();
    } else if (action === 'delete') {
        document.getElementById('eliminaModalLabel').textContent = `Delete instructor ${instructorId}`;
        const modal = new bootstrap.Modal(document.getElementById('eliminaModal'));
        modal.show();
    }
});

// Guardar cambios
guardarCambiosBtn.addEventListener('click', async () => {
    const instructorId = editInstructorIdInput.value;
    const firstName = editFirstNameInput.value.trim();
    const lastName = editLastNameInput.value.trim();

    if (!firstName || !lastName) {
        alert("Both First Name and Last Name are required.");
        return;
    }

    try {
        const res = await fetch(`${api}/api/v1/instructors/${instructorId}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ first_name: firstName, last_name: lastName })
        });

        if (res.ok) {
            console.log('Instructor updated');
            cargarInstructores();
            bootstrap.Modal.getInstance(document.getElementById('editarModal')).hide();
        } else {
            console.error('Error updating instructor');
            alert('Error updating instructor.');
        }
    } catch (error) {
        console.error('Error updating instructor:', error);
    }
});

// Eliminar instructor
eliminarInstructorBtn.addEventListener('click', async (event) => {
    event.preventDefault();

    const recaptchaResponse = grecaptcha.getResponse(deleteCaptchaWidgetId);
    if (!recaptchaResponse) {
        alert("Complete the reCAPTCHA to continue.");
        return;
    }

    if (!selectedInstructorId) {
        alert("No instructor selected for deletion.");
        return;
    }

    const instructorWithCaptchaDTO = {
        instructor: { id_instructor: selectedInstructorId },
        recaptchaToken: recaptchaResponse
    };

    try {
        const res = await fetch(`${api}/api/v1/instructors/`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(instructorWithCaptchaDTO)
        });

        if (res.ok) {
            const data = await res.json();
            console.log('Instructor deleted:', data);
            alert(data.message);
            cargarInstructores();
            bootstrap.Modal.getInstance(document.getElementById('eliminaModal')).hide();
        } else {
            const err = await res.json();
            console.error('Error deleting instructor:', err);
            alert(err.message || "Error deleting instructor.");
        }
    } catch (error) {
        console.error('Error in delete request:', error);
    } finally {
        grecaptcha.reset(deleteCaptchaWidgetId);
    }
});
const api = 'http://172.30.5.122:8080';

// Elementos DOM
const mostrarRegistro = document.getElementById('mostrarRegister');
const agregarLearner = document.getElementById('agregarLearner');
const cerrarModal = document.getElementById('cerrarModal');
const lista = document.getElementById('lista');
const editFirstNameInput = document.getElementById('edit-first_name');
const editLastNameInput = document.getElementById('edit-last_name');
const editLearnerIdInput = document.getElementById('edit-learnerId');
const guardarCambiosBtn = document.getElementById('guardarCambios');
const eliminarLearnerBtn = document.getElementById('eliminarLearner');

// Captcha widgets
let formCaptchaWidgetId = null;
let deleteCaptchaWidgetId = null;

// ID del learner seleccionado (global)
let selectedLearnerId = null;

// Cargar captchas
function onCaptchaLoadCallback() {
    formCaptchaWidgetId = grecaptcha.render('formCaptcha', {
        'sitekey': '6LeADR4rAAAAAD0c9CIg4-8uRh6jMAnHzJTyzfzq'
    });
    deleteCaptchaWidgetId = grecaptcha.render('deleteCaptcha', {
        'sitekey': '6LeADR4rAAAAAD0c9CIg4-8uRh6jMAnHzJTyzfzq'
    });
}

// Mostrar modal agregar learner
document.addEventListener('DOMContentLoaded', () => {
    mostrarRegistro.addEventListener('click', () => {
        agregarLearner.classList.add('content-modal-activo');
        document.body.classList.add('modal-open');
    });

    cerrarModal.addEventListener('click', () => {
        agregarLearner.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
    });

    cargarLearners();
    setInterval(cargarLearners, 10000);
});

// Agregar learner
document.getElementById('guardarLearner').addEventListener('click', async (event) => {
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
        alert("Completa el reCAPTCHA para continuar.");
        return;
    }

    const nuevoLearner = {
        learner: {
            first_name: firstName,
            last_name: lastName,
            status: 1
        },
        recaptchaToken: recaptchaResponse
    };

    try {
        const res = await fetch(`${api}/api/v1/learners/`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevoLearner)
        });
        const data = await res.json();
        console.log('Learner creado:', data);

        document.getElementById('first_name').value = '';
        document.getElementById('last_name').value = '';
        agregarLearner.classList.remove('content-modal-activo');
        document.body.classList.remove('modal-open');
        grecaptcha.reset(formCaptchaWidgetId);
        cargarLearners();
    } catch (error) {
        console.error('Error creando learner:', error);
    }
});

// Cargar learners
async function cargarLearners() {
    try {
        const res = await fetch(`${api}/api/v1/learners/`);
        const learners = await res.json();

        lista.innerHTML = '';
        learners.forEach(learner => {
            const tr = document.createElement('tr');

            tr.innerHTML = `
                <td>${learner.id_learner}</td>
                <td>${learner.first_name}</td>
                <td>${learner.last_name}</td>
                <td>
                    <button class="btn btn-warning btn-sm me-2" data-id="${learner.id_learner}" data-firstname="${learner.first_name}" data-lastname="${learner.last_name}" data-action="edit">Editar</button>
                    <button class="btn btn-danger btn-sm" data-id="${learner.id_learner}" data-action="delete">Eliminar</button>
                </td>
            `;
            lista.appendChild(tr);
        });
    } catch (error) {
        console.error('Error cargando learners:', error);
    }
}

// Manejar clics en la tabla
lista.addEventListener('click', (event) => {
    const action = event.target.dataset.action;
    const learnerId = event.target.dataset.id;
    const firstName = event.target.dataset.firstname;
    const lastName = event.target.dataset.lastname;

    if (!action) return;

    selectedLearnerId = learnerId;

    if (action === 'edit') {
        editLearnerIdInput.value = learnerId;
        editFirstNameInput.value = firstName;
        editLastNameInput.value = lastName;
        const modal = new bootstrap.Modal(document.getElementById('editarModal'));
        modal.show();
    } else if (action === 'delete') {
        document.getElementById('eliminaModalLabel').textContent = `Eliminar learner ${learnerId}`;
        const modal = new bootstrap.Modal(document.getElementById('eliminaModal'));
        modal.show();
    }
});

// Guardar cambios
guardarCambiosBtn.addEventListener('click', async () => {
    const learnerId = editLearnerIdInput.value;
    const firstName = editFirstNameInput.value.trim();
    const lastName = editLastNameInput.value.trim();

    if (!firstName || !lastName) {
        alert("Ambos campos de nombre y apellido son necesarios.");
        return;
    }

    try {
        const res = await fetch(`${api}/api/v1/learners/${learnerId}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ first_name: firstName, last_name: lastName })
        });

        if (res.ok) {
            console.log('Learner actualizado');
            cargarLearners();
            bootstrap.Modal.getInstance(document.getElementById('editarModal')).hide();
        } else {
            console.error('Error al actualizar el learner');
            alert('Error al actualizar el learner.');
        }
    } catch (error) {
        console.error('Error actualizando learner:', error);
    }
});

// Eliminar learner
eliminarLearnerBtn.addEventListener('click', async (event) => {
    event.preventDefault();

    const recaptchaResponse = grecaptcha.getResponse(deleteCaptchaWidgetId);
    if (!recaptchaResponse) {
        alert("Completa el reCAPTCHA para continuar.");
        return;
    }

    if (!selectedLearnerId) {
        alert("No se encontró el learner a eliminar.");
        return;
    }

    const learnerWithCaptchaDTO = {
        learner: { id_learner: selectedLearnerId },
        recaptchaToken: recaptchaResponse
    };

    try {
        const res = await fetch(`${api}/api/v1/learners/`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(learnerWithCaptchaDTO)
        });

        if (res.ok) {
            const data = await res.json();
            console.log('Learner eliminado:', data);
            alert(data.message);
            cargarLearners();
            bootstrap.Modal.getInstance(document.getElementById('eliminaModal')).hide();
        } else {
            const err = await res.json();
            console.error('Error eliminando learner:', err);
            alert(err.message || "Error eliminando learner.");
        }
    } catch (error) {
        console.error('Error en solicitud de eliminación:', error);
    } finally {
        grecaptcha.reset(deleteCaptchaWidgetId);
    }
});

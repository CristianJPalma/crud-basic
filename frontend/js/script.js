// Abrir y cerrar la barra lateral
function openNav() {
    document.getElementById("sidebar").style.width = "250px";
}

function closeNav() {
    document.getElementById("sidebar").style.width = "0";
}

// Cambiar el contenido según la página seleccionada
function changeContent(page) {
    switch (page) {
        case 'enrollment':
            location.href = '/enrollment/enrollment.html';
            break;
        case 'learner':
            location.href = '/learner/learner.html';
            break;
        case 'instructor':
            location.href = '/instructor/instructor.html';
            break;
        case 'course':
            location.href = '../course/course.html';
            break;
        case 'schedule': // Corregido de "scheldule"
            location.href = '../schedule/schedule.html';
            break;
        default:
            console.error("Página no encontrada:", page);
            break;
    }
    closeNav();
}





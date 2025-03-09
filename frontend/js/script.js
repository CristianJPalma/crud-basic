function openNav() {
    document.getElementById("sidebar").style.width = "250px";
}

function closeNav() {
    document.getElementById("sidebar").style.width = "0";
}

function changeContent(page) {
    var content = document.getElementById("content");

    switch(page) {
        case 'enrollment':
            location.href = '/enrollment/enrollment.html'
            break;
        case 'learner':
            location.href = '/learner/learner.html'
            break;
        case 'instructor':
            location.href = '/instructor/instructor.html'
            break;
        case 'course':
            location.href = '../course/course.html'
            break;
        case 'scheldule':
            location.href = '../scheldule/scheldule.html'
            break;
        default:
            break;
    }

    closeNav();

}

document.getElementById("mostrarRegister").addEventListener("click", function() {
    let contenido = document.getElementById("contenido");

    if (contenido.style.display === "none" || contenido.style.display === "") {
        contenido.style.display = "block";
    } else {
        contenido.style.display = "none";
    }
});


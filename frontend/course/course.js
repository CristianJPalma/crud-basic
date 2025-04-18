
fetch('http://localhost:8080/api/v1/courses/')
    .then(respuesta => respuesta.json())
    .then(datos => {
        console.log('Cursos disponibles', datos);
    })
    .catch(error => console.log(error));
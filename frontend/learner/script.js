function openNav() {
    document.getElementById("sidebar").style.width = "250px";
}

function closeNav() {
    document.getElementById("sidebar").style.width = "0";
}

function changeContent(page) {
    var content = document.getElementById("content");

    switch(page) {
        case 'inicio':
            content.innerHTML = `
                <h1>Bienvenido a nuestra página</h1>
                <p>Este es el inicio. Aquí puedes encontrar una introducción sobre lo que hacemos.</p>
            `;
            break;
        case 'servicios':
            content.innerHTML = `
                <h1>Servicios</h1>
                <p>Ofrecemos una variedad de servicios, como desarrollo web, diseño gráfico y consultoría.</p>
            `;
            break;
        case 'acerca':
            content.innerHTML = `
                <h1>Acerca de nosotros</h1>
                <p>Somos una empresa dedicada a crear soluciones digitales innovadoras y eficientes.</p>
            `;
            break;
        case 'contacto':
            content.innerHTML = `
                <h1>Contacto</h1>
                <p>Si tienes alguna pregunta, no dudes en ponerte en contacto con nosotros a través de nuestro correo electrónico.</p>
            `;
            break;
        default:
            content.innerHTML = `
                <h1>Bienvenido a nuestra página</h1>
                <p>Selecciona una opción del menú para ver más detalles.</p>
            `;
            break;
    }

    closeNav(); // Cerrar el menú lateral después de seleccionar una opción
}

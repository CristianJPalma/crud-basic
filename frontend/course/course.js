document.addEventListener('DOMContentLoaded', function()); {
            const lista = document.getElementById('productos-lista');

            function cargarProductos() {
                fetch('http://172.30.7.20:8080/api/productos')
                    .then(response => response.json())
                    .then(data => {
                        lista.innerHTML = '';
                        data.forEach(producto => {
                            const li = document.createElement('li');
                            li.textContent = ${producto.id} - ${producto.nombre} - Precio: $${producto.precio} - Cantidad: ${producto.cantidad} - Categoría: ${producto.categoria};
                            lista.appendChild(li);
                        });
                    });
            }}
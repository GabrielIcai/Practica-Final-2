const form = document.querySelector('#formulario');

const handleForm = async () => {
  event.preventDefault();

  const nombre = document.querySelector('#nombre').value.trim();
  const email = document.querySelector('#email').value.trim();
  const credenciales = "Basic " + btoa(document.querySelector('#nombre').value.trim() + ":" + document.querySelector('#credenciales').value.trim());
  const rol = 'USER';

  if (!nombre || !email || !credenciales) {
    // Si alguno de los campos está vacío
    showModal('Falta completar uno o más campos');
  } else {
    // Si todos los campos están completos
    const basicAuthValue = btoa(`${nombre}:${credenciales}`); // Codifica el email y la contraseña en Base64

    const usuario = { nombre, email, credenciales, rol };

    const response = await fetch('/api/registrarusuario', {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(usuario)
    });

    if (response.ok) {
      // Si la solicitud se envía con éxito
      showModal('Usuario creado con éxito');
      // Borrar campos del formulario
      document.querySelector('#nombre').value = '';
      document.querySelector('#email').value = '';
      document.querySelector('#credenciales').value = '';
      //document.querySelector('#rol').value = '';
    } else {
      console.error(response.statusText);
    }
  }
};

 const showModal = (message) => {
   // Crear modal con Bootstrap
   const modal = document.createElement('div');
   modal.classList.add('modal', 'fade');
   modal.setAttribute('tabindex', '-1');
   modal.setAttribute('role', 'dialog');
   modal.setAttribute('aria-labelledby', 'modalLabel');
   modal.setAttribute('aria-hidden', 'true');

   modal.innerHTML = `
     <div class="modal-dialog" role="document">
       <div class="modal-content">
         <div class="modal-header">
           <h5 class="modal-title" id="modalLabel">Aviso</h5>
           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
             <span aria-hidden="true">&times;</span>
           </button>
         </div>
         <div class="modal-body">
           ${message}
         </div>
         <div class="modal-footer">
           <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
         </div>
       </div>
     </div>
   `;

   document.body.appendChild(modal);

   // Mostrar modal con Bootstrap 4
   $(modal).modal('show');

   // Eliminar modal del DOM cuando se cierre
   $(modal).on('hidden.bs.modal', () => {
     modal.remove();
   });
 };
  document.getElementById("formulario").addEventListener("submit", function(event) {
    event.preventDefault(); // Evitar el envío del formulario por defecto
    // Simulación del envío del formulario
    setTimeout(function() {
      // Aquí iría la lógica de envío de datos al servidor
      // Si el envío es exitoso, redirigir a la página de inicio
      window.location.href = "inicio.html";  
    }, 1000);  
    });


document.querySelector('#formulario').addEventListener('submit', handleForm);

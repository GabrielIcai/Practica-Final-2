document.getElementById('registroForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const nombre = document.getElementById('nombre').value;
    const email = document.getElementById('email').value;
    const contraseña = document.getElementById('contraseña').value;
    const piso = document.getElementById('piso').value;
    const letra = document.getElementById('letra').value;

    const data = {
        nombre: nombre,
        emailR: email,
        credencialesR: contraseña,
        piso: piso,
        letra: letra
    };

    fetch('/api/registrarusuario', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.status === 201) {
            document.getElementById('mensaje').innerText = 'Usuario registrado exitosamente. Redirigiendo al login...';
            setTimeout(() => {
                window.location.href = 'login.html';
            }, 2000); // Redirige después de 2 segundos
        } else {
            document.getElementById('mensaje').innerText = 'Error al registrar el usuario.';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('mensaje').innerText = 'Error al registrar el usuario.';
    });
});

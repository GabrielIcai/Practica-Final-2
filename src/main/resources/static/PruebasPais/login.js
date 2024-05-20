document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const email = document.getElementById('email').value;
    const contraseña = document.getElementById('contraseña').value;

    const data = {
        email: email,
        credenciales: contraseña
    };

    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.status === 200) {
            response.json().then(user => {
                document.getElementById('mensaje').innerText = 'Inicio de sesión exitoso. Redirigiendo...';
                // Guardar el token y redirigir al dashboard
                localStorage.setItem('token', user.token);
                setTimeout(() => {
                    window.location.href = 'inicio.html'; // Cambia a la URL del dashboard
                }, 2000); // Redirige después de 2 segundos
            });
        } else {
            document.getElementById('mensaje').innerText = 'Email o contraseña incorrectos.';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('mensaje').innerText = 'Error al iniciar sesión.';
    });
});


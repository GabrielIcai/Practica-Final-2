document.getElementById("crearPlantaForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const nombre = document.getElementById("nombre").value;
    const duracion = parseInt(document.getElementById("duracion").value);

    // Hacer una solicitud POST a la API para crear una nueva planta
    fetch("/api/crearPlanta", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nombre: nombre,
            duracion: duracion
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error al crear la planta");
        }

        window.location.href = "app.html";
    })
    .catch(error => console.error("Error al crear la planta:", error));
});

document.addEventListener("DOMContentLoaded", function() {
    // Hacer una solicitud a la API para obtener la lista de plantas
    fetch("/api/plantas")
        .then(response => response.json())
        .then(data => {
            const plantasBody = document.getElementById("plantasBody");
            data.forEach(planta => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${planta.nombre}</td>
                    <td>${planta.Regada ? "Regada" : "No Regada"}</td>
                    <td>${planta.appUser.name}</td>
                    <td>${planta.appUser.piso}</td>
                    <td>${planta.appUser.letra}</td>
                    <td><button onclick="regarPlanta(${planta.id})">Regar</button></td>
                `;
                plantasBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error al obtener las plantas:", error));
});

function regarPlanta(id) {
    // Hacer una solicitud PUT a la API para regar la planta con el ID especificado
    fetch(`/api/plantas/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({})
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error al regar la planta");
        }
        // Recargar la página para actualizar el estado de la planta
        location.reload();
    })
    .catch(error => console.error("Error al regar la planta:", error));
}
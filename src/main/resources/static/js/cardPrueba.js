fetch('jdbc:mysql://localhost:3306/ayudaencasa')
    .then(response => response.json())
    .then(data => {
        crearTarjetas(data.results)
    })

/* function crearColor(edad) {
    if (edad <= 3) {
        return 'nuevo'
    } else if (edad <= 5) {
        return 'medio'
    } else if (edad <= 10) {
        return 'avanzado'
    } else if (edad <= 15) {
        return 'pro'
    } else {
        return 'maestro'
    }
} */

function crearTarjetas(user) {
    let container = document.querySelector('.container')
    user.forEach(user => {
        container.innerHTML += /*html*/`
        <div class="card">
            <div class="phone"> ${user.phone}</div>
            <div class="card-titulo">
                <h1>${user.firstName} ${user.lastName} </h1>
            </div>
            <div class= "card-body">
                <div class= "card-body-items">
                    <div class= "datos-job">
                    <p>${user.salary}</p>
                    <p>${user.workingZone}</p>
                    <p>${user.dateFrom}</p>
                    <p>${user.dateTo}</p>
                </div>
                </div>                
                <img src="https://www.argentina.gob.ar/sites/default/files/vinetas_justicia_cerca_04_el_paseador_de_mi_perro.png" alt="">
            </div>
            <div class="card-footer">
                <p>${user.petQuantity}</p>
                <p>${user.petType}</p>
            </div>
        </div>
    



    /* <div class="card ${crearColor(usuario.registered.age)}">
            <div class="puntos">...</div>
            <div class="card-body">
                <div class="card-body-item">
                    <img src="${usuario.picture.large}"
                        alt="" />
                    <div clas="texto-medio">
                        <h5 class="card-title">${usuario.name.first} ${usuario.name.last}</h5>
                        <p class="phone">${usuario.phone}</p>
                        <div class="datos">
                            <div>
                                <p>${usuario.gender}</p>
                                <p>Gender</p>
                            </div>
                            <div>
                                <p>${usuario.nat}</p>
                                <p>Nat</p>
                            </div>
                            <div>
                                <p>${usuario.location.postcode}</p>
                                <p>Postcode</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ranking">
                    <h4>${usuario.registered.age}</h4>
                    <p>Age</p>
                </div>
            </div>
            <div class="circulo ${crearColor(usuario.registered.age)}"></div>
        </div> */
    `
    })

    console.log(usuarios)
}


let date = new Date()

let months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']


console.log(months[date.getMonth()])

console.log(date.toLocaleDateString('ES'))
© 2022 GitHub, Inc.
Terms
Privacy
Security

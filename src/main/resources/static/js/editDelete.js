"use strict"

let modal = document.getElementById("editUser");
let form = document.getElementById("editUserForm");

let openModal = () => {
    modal.style.display = "block";
}

let closeModal = () => {
    modal.style.display = "none";
}
const userId = form.querySelector('[name="id"]'),
    name = form.querySelector('[name="username"]'),
    password = form.querySelector('[name="password"]'),
    age = form.querySelector('[name="age"]'),
    email = form.querySelector('[name="email"]'),
    role = form.querySelector('[name="roles"]');

async function editUser(id) {
    openModal()

    await fetch(`/admin/${id}`)
        .then(response => response.json())
        .then(user => {
            userId.value = user.id;
            name.value = user.username;
            // password.textContent = "****";
            age.value = user.age;
            email.value = user.email;
        });
}

fetch("admin/roles")
    .then((response) => response.json())
    .then((data) => {
        let select = document.getElementById('selectEdit');
        for (let role of data) {
            select.insertAdjacentHTML("afterbegin", `
                    <option value="${role.id}">${role.name}</option>
                `)
        }
    });

async function saveChange() {

    let roles = {id: role.value, name: ""};

    const value = {
        id: userId.value,
        username: name.value,
        password: password.value,
        age: age.value,
        email: email.value,
        roles: [roles]
    }

    await fetch("/admin/edit", {
        method: "PATCH",
        headers: {
            "Content-type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(value)
    });

    closeModal();
    openTable();
}

async function deleteUser(id) {
    await fetch('/admin/' + id, {
        method: 'DELETE'
    });
}
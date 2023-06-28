"use strict"

let modal = document.getElementById("editUser");
let form = document.getElementById("editUserForm");

let openModal = () => {
    modal.style.display = "block";
}

let closeModal = () => {
    modal.style.display = "none";
}

function editUser(id) {
    openModal()

    const userId = form.querySelector('[name="id"]'),
        name = form.querySelector('[name="username"]'),
        age = form.querySelector('[name="age"]'),
        email = form.querySelector('[name="email"]');

    fetch(`/admin/${id}`)
        .then(response => response.json())
        .then(user => {
            userId.value = user.id;
            name.value = user.username;
            age.value = user.age;
            email.value = user.email;
        });

    fetch("admin/roles")
        .then((response) => response.json())
        .then((data) => {
            let select = document.getElementById('selectEdit');
            select.innerHTML = "";
            for (let role of data) {
                select.insertAdjacentHTML("afterbegin", `
                    <option value="${role.id}">${role.name}</option>
                `)
            }
        });
}



async function saveChange() {
    const userId = form.querySelector('[name="id"]'),
        name = form.querySelector('[name="username"]'),
        password = form.querySelector('[name="password"]'),
        age = form.querySelector('[name="age"]'),
        email = form.querySelector('[name="email"]'),
        role = form.querySelector('[name="roles"]');

    let roles = rolesMultiple(role);

    const value = {
        id: userId.value,
        username: name.value,
        password: password.value,
        age: age.value,
        email: email.value,
        roles: roles
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




let modalDelete = document.getElementById("deleteUser");
let formDelete = document.getElementById("deleteUserForm");

let openModalDelete = () => {
    modalDelete.style.display = "block";
}

let closeModalDelete = () => {
    modalDelete.style.display = "none";
}

async function deleteUserForm(id) {
    openModalDelete()

    const userId = formDelete.querySelector('[name="id"]'),
        name = formDelete.querySelector('[name="username"]'),
        age = formDelete.querySelector('[name="age"]'),
        email = formDelete.querySelector('[name="email"]');



    fetch(`/admin/${id}`)
        .then(response => response.json())
        .then(user => {
            userId.value = user.id;
            name.value = user.username;
            age.value = user.age;
            email.value = user.email;
            let select = document.getElementById('selectDelete');
            select.innerHTML = "";
            for (let role of user.roles) {
                select.insertAdjacentHTML("afterbegin", `
                    <option value="${role.id}">${role.name}</option>
                `)
            }
        });
}

async function deleteUser() {
    const userId = formDelete.querySelector('[name="id"]');

    const value = {
        id: userId.value,
        username: "",
        password: "",
        age: "",
        email: "",
        roles: []
    }

    await fetch("/admin/delete", {
        method: 'DELETE',
        headers: {
            "Content-type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(value)
    });

    closeModalDelete();
    openTable();
}
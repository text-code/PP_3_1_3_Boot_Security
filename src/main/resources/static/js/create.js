"use strict"

fetch("admin/roles")
    .then((response) => response.json())
    .then((data) => {
        let select = document.getElementById('select');
        for (let role of data) {
            select.insertAdjacentHTML("afterbegin", `
                    <option value="${role.id}">${role.name}</option>
                `)
        }
    });

async function sendForm() {

    let form = document.getElementById('registrationForm');

    const name = form.querySelector('[name="username"]'),
        password = form.querySelector('[name="password"]'),
        age = form.querySelector('[name="age"]'),
        email = form.querySelector('[name="email"]'),
        role = form.querySelector('[name="roles"]');

    let roles = rolesMultiple(role);

    const value = {
        username: name.value,
        password: password.value,
        age: age.value,
        email: email.value,
        roles: roles
    }

    await fetch('admin/registration', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(value)
    });

    openTable();
}

function rolesMultiple(role) {
    let roles = [];

    if (role.multiple) {
        for (let i = 0; i < role.options.length; i++) {
            if(role.options[i].selected) {
                roles.push({id: role.options[i].value, name: ""});
            }
        }
    } else {
        roles.push(role.value);
    }

    return roles;
}
"use strict"

let tbody = document.getElementById("userTableBody");
let role = document.getElementById("userRole");


fetch("/user/current")
    .then(request => request.json())
    .then(user => {
        let roles = () => {
            let result = '';
            for (let role of user.roles) {
                result += (result === '') ? role.name : ', ' + role.name;
            }
            return result;
        };

        role.innerText = `${user.email} with roles: ${roles()}`;

        function userRole() {
            let result = '';
            for (let i = 0; i < user.roles.length; i++) {
                result += (i > 0) ? ', ' + user.roles[i].name : user.roles[i].name;
            }
            return result;
        }

        tbody.insertAdjacentHTML("beforeend", `
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>****</td>
                        <td>${userRole()}</td>
                        <td>
                    </tr>
                `);
    });
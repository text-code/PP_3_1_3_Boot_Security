"use strict"

window.addEventListener("load", () => {
    openTable();
});

fetch("/admin/current")
    .then(response => response.json())
    .then(data => {
        let role = document.getElementById('adminRole');

        let roles = () => {
            let result = '';
            for (let role of data.roles) {
                result += (result === '') ? role.name : ', ' + role.name;
            }
            return result;
        };

        role.innerText = `${data.email} with roles: ${roles()}`;
    });

function createTableBody() {
    let tbody = document.getElementById("adminTableBody");

    tbody.innerHTML = "";

    fetch('admin/all')
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            for (let user of data) {
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
                            <div>
                                <input onclick="editUser(${user.id})" 
                                class="btn btn-primary" type="submit" value="Edit"/>
                            </div>
                        </td>
                        <td>
                            <div>
                                <input onclick="deleteUserForm(${user.id})" 
                                class="btn btn-secondary" type="submit" value="Delete"/>
                            </div>
                        </td>
                    </tr>
                `);
            }
        });
}

let checkUsersTable = document.getElementById('checkUsersTable');
let checkRegForm = document.getElementById('checkNewUser');

let newUser = document.getElementById('newUser');
let userTable = document.getElementById('userTable');

let openTable = () => {
    createTableBody();
    checkRegForm.classList.remove("active");
    checkUsersTable.classList.add("active");
    userTable.style.display = "block";
    newUser.style.display = "none";
};
let openRegForm = () => {
    checkUsersTable.classList.remove("active");
    checkRegForm.classList.add("active");
    userTable.style.display = "none";
    newUser.style.display = "block";
};
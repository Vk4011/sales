function validateLogin() {
    let loginData = {
        email: document.getElementById("employeeId").value,
        password: document.getElementById("password").value
    };

    fetch("/api/users/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(loginData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.message === "Login successful") {
            window.location.href = getRoleRedirectPage();
        } else {
            document.getElementById("loginError").style.display = "block";
        }
    })
    .catch(error => console.error("Error:", error));
}

function forgotPassword() {
    alert("Redirecting to forgot password page...");
}

function getQueryParam(param) {
    let urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

function redirectToDashboard(role) {
    if (role === "Billing Desk") {
        window.location.href = "billing.html";
    } else if (role === "Admin") {
        window.location.href = "admin.html";
    } else if (role === "Sales Person") {
        window.location.href = "sales.html";
    } else {
        alert("Invalid role selection.");
    }
}

window.onload = function () {
    let roleParam = getQueryParam("role");
    let roleDisplay = document.getElementById("roleDisplay");
    if (roleParam) {
        roleDisplay.textContent = `Logging in as: ${decodeURIComponent(roleParam)}`;
    }
};
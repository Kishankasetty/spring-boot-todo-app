const API_URL = "/api/tasks";
let allTasks = [];

/* =========================
   LOAD ALL TASKS
========================= */

async function loadTasks() {
    showMessage("Loading tasks...", "loading");

    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error(`Server returned ${response.status}`);
        }

        allTasks = await response.json();
        filterTasks();
        showMessage("");

    } catch (error) {
        console.error("Error loading tasks:", error);
        showMessage("Could not load tasks. Please try again.", "error");
    }
}

function filterTasks() {
    const searchInput = document.getElementById("searchInput");
    const statusFilter = document.getElementById("statusFilter");

    const searchText = searchInput.value.trim().toLowerCase();
    const selectedStatus = statusFilter.value;

    const filteredTasks = allTasks.filter(task => {
        const matchesSearch = task.title
            .toLowerCase()
            .includes(searchText);

        const matchesStatus =
            selectedStatus === "ALL" ||
            (selectedStatus === "PENDING" && !task.completed) ||
            (selectedStatus === "COMPLETED" && task.completed);

        return matchesSearch && matchesStatus;
    });

    displayTasks(filteredTasks);
}



/* =========================
   DISPLAY TASKS
========================= */

function displayTasks(tasks) {
    const taskList = document.getElementById("taskList");
    taskList.innerHTML = "";

    tasks.forEach(task => {
        const li = document.createElement("li");

        // Task title
        const title = document.createElement("span");
        title.textContent = task.title;

        if (task.completed) {
            title.classList.add("completed");
        }

        // Task details
        const details = document.createElement("small");

        const priority = task.priority || "MEDIUM";
        const dueDate = task.dueDate
            ? ` | Due: ${task.dueDate}`
            : " | No due date";

        details.textContent = `Priority: ${priority}${dueDate}`;

        // Complete / Undo button
        const completeButton = document.createElement("button");
        completeButton.textContent = task.completed ? "Undo" : "Complete";
        completeButton.onclick = () => toggleTask(task);

        // Edit button
        const editButton = document.createElement("button");
        editButton.textContent = "Edit";
        editButton.onclick = () => editTask(task);

        // Delete button
        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Delete";
        deleteButton.onclick = () => deleteTask(task.id);

        li.appendChild(title);
        li.appendChild(details);
        li.appendChild(completeButton);
        li.appendChild(editButton);
        li.appendChild(deleteButton);

        taskList.appendChild(li);
    });
}


function showMessage(message, type = "") {
    const statusMessage = document.getElementById("statusMessage");

    statusMessage.textContent = message;
    statusMessage.className = type;
}


/* =========================
   ADD TASK
========================= */

async function addTask() {
    const input = document.getElementById("taskInput");
    const priorityInput = document.getElementById("priorityInput");
    const dueDateInput = document.getElementById("dueDateInput");

    const title = input.value.trim();
    const priority = priorityInput.value;
    const dueDate = dueDateInput.value;

    if (title === "") {
        showMessage("Please enter a task title.", "error");
        return;
    }

    const taskData = {
        title: title,
        completed: false,
        priority: priority,
        dueDate: dueDate || null
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(taskData)
        });

        if (!response.ok) {
    const errorBody = await response.text();

    console.error("HTTP Status:", response.status);
    console.error("Server response:", errorBody);

    throw new Error(
        `Request failed with status ${response.status}`
    );
}

        input.value = "";
        priorityInput.value = "MEDIUM";
        dueDateInput.value = "";

        await loadTasks();
        showMessage("Task created successfully.", "success");

    } catch (error) {
        console.error("Error creating task:", error);
        showMessage("Could not create the task. Please try again.", "error");
    }
}



/* =========================
   COMPLETE / UNCOMPLETE
========================= */


async function toggleTask(task) {

    await fetch(`${API_URL}/${task.id}`, {

        method: "PUT",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            title: task.title,

            completed: !task.completed

        })
    });

    loadTasks();
}
 

/* =========================
   EDIT TASK
========================= */

async function editTask(task) {

    const newTitle = prompt(
        "Edit task:",
        task.title
    );

    if (newTitle === null) {
        return;
    }

    const trimmedTitle = newTitle.trim();

    if (trimmedTitle === "") {
        alert("Task cannot be empty.");
        return;
    }


    await fetch(`${API_URL}/${task.id}`, {

        method: "PUT",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            title: trimmedTitle,

            completed: task.completed

        })
    });

    loadTasks();
}
  


/* =========================
   DELETE TASK
========================= */

async function deleteTask(id) {

    const confirmDelete =
        confirm("Are you sure you want to delete this task?");

    if (!confirmDelete) {
        return;
    }


    await fetch(`${API_URL}/${id}`, {

        method: "DELETE"
    });


    loadTasks();
}
 

/* =========================
   INITIAL LOAD
========================= */
document.getElementById("searchInput")
    .addEventListener("input", filterTasks);

document.getElementById("statusFilter")
    .addEventListener("change", filterTasks);

loadTasks();

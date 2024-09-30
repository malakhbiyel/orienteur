class LessonsTable {
    constructor(tableId) {
        this.tableElement = document.getElementById(tableId);
        this.lessons = new Map();
    }

    insert(lesson) {
        if (!this.lessons.has(lesson.id)) { // Éviter les doublons par ID
            const row = this.tableElement.insertRow(1); // Insérer à la fin
            row.insertCell(0).innerHTML = `<input type="radio" name="lessonSelect">`;
            row.insertCell(1).textContent = lesson.title;
            row.insertCell(2).textContent = lesson.note;

            this.lessons.set(lesson.id, lesson);
        }
    }


    populate(lessons) {
        lessons.forEach(lesson => this.insert(lesson));
    }
}


function getAll() {
    const REPLIED = 4;
    const HTTP_OK = 200;
    const xhr = new XMLHttpRequest();
    const lessonsTable = new LessonsTable('table');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === REPLIED) {
            switch (xhr.status) {
                case HTTP_OK:
                    const lessons = JSON.parse(xhr.responseText);
                    lessonsTable.populate(lessons);
                    break;
                default:
                    console.error('Erreur lors de la récupération des leçons:', xhr.status);
            }
        }
    };

    try {
        xhr.open("GET", "/api/lessons/All", true);
        xhr.send();
    } catch (err) {
        alert(err.name + " " + err.message);
    }
}


document.addEventListener('DOMContentLoaded', getAll);

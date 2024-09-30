class Counter {
    constructor(value) {
        this._value = value;
    }

    get value() {
        return this._value;
    }

    up() {
        this._value++;
    }

    clear() {
        this._value = 0;
    }

    toString() {
        return `${this.value}`;
    }
}

class OverflowCounter extends Counter {
    constructor(value, max) {
        super(value);
        this._max = max;
        this._overflow = false;
    }

    up() {
        if (this.value < this._max)
            super.up();
        else
            this._overflow = true;
    }

    overflows() {
        return this._overflow;
    }

    toString() {
        return `${this.value} ${this.overflows() ? "*" : ""}`;
    }

    toJSON() {
        return `{ "value" : ${this.value}, "max" : ${this._max} }`;
    }
}

const cnt = new OverflowCounter(0, 5);

function count(event) {
    cnt.up();
    counter.innerHTML = `${cnt}`;
}

function clear(event) {
    cnt.clear();
    counter.innerHTML = `${cnt}`;
}

console.log(JSON.stringify(cnt));

const btnUp = document.querySelector("#idUp");
const btnClear = document.querySelector("#idClear");
const counter = document.querySelector("#idCounter");

btnUp.addEventListener("click", count);
btnClear.addEventListener("click", clear);

const REPLIED = 4;
const HTTP_OK = 200;

function getAll() {
    const xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (xhr.readyState == REPLIED) {
            if (xhr.status == HTTP_OK) {
                console.log(xhr.responseText);
                const response = JSON.parse(xhr.responseText);
                console.log(response);
            } else {
                console.error("Erreur HTTP : " + xhr.status);
            }
        }
    };

    try {
        xhr.open("GET", "/api/lessons/showAll", true);
        xhr.send();
    } catch (err) {
        alert(err.name + " " + err.message);
    }
}

getAll();

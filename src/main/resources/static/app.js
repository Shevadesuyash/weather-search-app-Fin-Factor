function getWeather() {
    const city = document.getElementById("cityInput").value.trim();
    const errorDiv = document.getElementById("error");
    const card = document.getElementById("weatherCard");

    errorDiv.innerText = "";
    card.classList.add("hidden");

    if (!city) {
        errorDiv.innerText = "Please enter a city name";
        return;
    }

    fetch(`/api/weather?city=${city}`)
        .then(response => {
            if (!response.ok) {
                return response.text().then(msg => { throw new Error(msg); });
            }
            return response.json();
        })
        .then(data => {
            document.getElementById("location").innerText =
                `${data.city}, ${data.country}`;

            document.getElementById("description").innerText =
                data.description;

            document.getElementById("temp").innerText = data.temperature;
            document.getElementById("feelsLike").innerText = data.feelsLike;
            document.getElementById("humidity").innerText = data.humidity;
            document.getElementById("pressure").innerText = data.pressure;
            document.getElementById("wind").innerText = data.windSpeed;
            document.getElementById("clouds").innerText = data.cloudiness;
            document.getElementById("visibility").innerText = data.visibility;

            document.getElementById("rawResponse").innerText =
                JSON.stringify(data.apiResponse, null, 2);

            card.classList.remove("hidden");
        })
        .catch(err => {
            errorDiv.innerText = err.message || "Something went wrong";
        });
}

const API_URL = "http://localhost:4111/api";

export const calculateDeposit = async (requestBody) => {
    const response = await fetch(
        `${API_URL}/calculate`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        }
    );

    const data = await response.json();

    if (!response.ok) {
        const error = new Error("Calculation failed");
        error.status = response.status;
        error.data = data;
        throw error;
    }

    return data;
};
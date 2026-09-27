import React from "react";

import CalculatorForm from "./components/CalculatorForm";
import CalculatorResult from "./components/CalculatorResult";

import { useDepositCalculator } from "./hooks/useDepositCalculator";

import "./styles/App.css";

function App() {
    const {
        requestBody,
        response,
        errors,
        handleInputChange,
        submit
    } = useDepositCalculator();

    return (
        <div className="App">
            <CalculatorForm
                requestBody={requestBody}
                errors={errors}
                onChange={handleInputChange}
                onSubmit={submit}
            />

            <CalculatorResult
                result={response}
                amount={requestBody.amount}
            />
        </div>
    );
}

export default App;
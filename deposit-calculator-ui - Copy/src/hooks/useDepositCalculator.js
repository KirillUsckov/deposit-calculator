import { useState } from "react";
import { calculateDeposit } from "../api/depositCalculatorApi";

export const useDepositCalculator = () => {
    const [requestBody, setRequestBody] = useState({
        amount: "",
        months: "",
        rate: ""
    });

    const [response, setResponse] = useState(null);
    const [errors, setErrors] = useState({});

    const handleInputChange = (field, value) => {
        setResponse(null);

        setRequestBody(prev => ({
            ...prev,
            [field]: value
        }));

        setErrors(prev => ({
            ...prev,
            [field]: undefined,
            general: undefined
        }));
    };

    const submit = async () => {
        setErrors({});
        setResponse(null);

        if (Object.values(requestBody).some(
            value => value === ""
        )) {
            setErrors({
                general: "Заполните все поля формы!"
            });
            return;
        }

        const body = {
            amount: Number(requestBody.amount),
            months: Number(requestBody.months),
            rate: Number(requestBody.rate)
        };

        try {
            const result = await calculateDeposit(body);
            setResponse(result);

        } catch (error) {
            if (error.status === 400 && error.data?.errors) {
                setErrors(error.data.errors);
            } else {
                setErrors({
                    general: "Не удалось выполнить расчет"
                });
            }
        }
    };

    return {
        requestBody,
        response,
        errors,
        handleInputChange,
        submit
    };
};
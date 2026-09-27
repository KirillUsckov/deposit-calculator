import InputWithName from "./InputWithName";

const CalculatorForm = ({
                            requestBody,
                            errors,
                            onChange,
                            onSubmit
                        }) => {

    const handleSubmit = (event) => {
        event.preventDefault();
        onSubmit();
    };

    return (
        <form
            className="calculator"
            onSubmit={handleSubmit}
        >
            <InputWithName
                description="Сумма вклада:"
                suffix="₽"
                inputMode="decimal"
                value={requestBody.amount}
                onChange={value =>
                    onChange("amount", value)
                }
                error={errors.amount}
            />

            <InputWithName
                description="Срок (месяцы):"
                inputMode="numeric"
                value={requestBody.months}
                onChange={value =>
                    onChange("months", value)
                }
                error={errors.months}
            />

            <InputWithName
                description="Годовая ставка:"
                suffix="%"
                inputMode="decimal"
                value={requestBody.rate}
                onChange={value =>
                    onChange("rate", value)
                }
                error={errors.rate}
            />

            {errors.general && (
                <p className="input-error">
                    {errors.general}
                </p>
            )}

            <button className="center-button" type="submit">Рассчитать</button>
        </form>
    );
};

export default CalculatorForm;
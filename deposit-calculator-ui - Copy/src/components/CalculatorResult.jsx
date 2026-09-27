import Label from "./Label";
import { formatMoney } from "../utils/formatMoney";

const CalculatorResult = ({ result, amount }) => {
    if (!result) return null;

    return (
        <div className="calculator-result">
            <Label
                description="Начальная сумма:"
                value={formatMoney(amount)}
                suffix="₽"
            />

            <Label
                description="Итоговая сумма:"
                value={formatMoney(result.total)}
                suffix="₽"
            />

            <Label
                description="Доход:"
                value={formatMoney(result.profit)}
                suffix="₽"
            />
        </div>
    );
};

export default CalculatorResult;
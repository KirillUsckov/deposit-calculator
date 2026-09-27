const formatter = new Intl.NumberFormat("ru-RU", {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
});

export const formatMoney = (value) => {
    return formatter.format(value);
};
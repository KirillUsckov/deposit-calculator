import React from 'react';
import '../styles/App.css';

const Label = (props) => {
    const handleChange = (event) => {
        const input = event.target.value;

            props.onChange(input);

    };
    return (
        <div className="calculator-container">
            <label>{props.description}</label>
            <span>{props.value}</span>
            <span>{props.suffix}</span>
        </div>
    );
}

export default Label;
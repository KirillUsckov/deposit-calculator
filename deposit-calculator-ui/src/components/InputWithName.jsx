import React from 'react';
import '../styles/App.css';

const InputWithName = (props) => {
    const handleChange = (event) => {
        const input = event.target.value;

        const patterns = {
            decimal: /^\d*(\.\d{0,2})?$/,
            numeric: /^\d*$/
        };

        const pattern = patterns[props.inputMode];

        if (!pattern || pattern.test(input)) {
            props.onChange(input);
        }
    };
    return (
        <div>
            <div className="calculator-container">
                <label>{props.description}</label>
                <div className="content-wrapper">
                    <input type="text" inputMode={props.inputMode} value={props.value} onChange={handleChange}/>
                    <span>{props.suffix}</span>
                </div>
            </div>
            {props.error && (
                <span className="input-error">{props.error}</span>
            )}
        </div>
    );
}

export default InputWithName;
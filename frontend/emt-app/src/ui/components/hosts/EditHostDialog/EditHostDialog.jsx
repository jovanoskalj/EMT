import React, { useEffect, useState } from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from "@mui/material";
import useCountries from "../../../../hooks/useCountries.js";

const EditHostDialog = ({ open, onClose, host, onEdit }) => {
    const [formData, setFormData] = useState({
        name: host.name,
        surname: host.surname,
        countryId: host.country.id
    });

    const { countries } = useCountries();

    console.log("Host:", host);
    console.log("Countries:", countries);
    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {

        onEdit(host.id, formData);
        setFormData(formData);
        onClose();
    };

    useEffect(() => {
        if (host && host.country) {
            setFormData({
                name: host.name || "",
                surname: host.surname || "",
                countryId: host.country.id || ""
            });
        }
    }, [host]);

    if (!host || !host.country || countries.length === 0) return null;
    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Host</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Surname"
                    name="surname"
                    value={formData.surname}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Country</InputLabel>
                    <Select
                        name="countryId"
                        value={formData.countryId || ""}
                        onChange={handleChange}
                        label="Country"
                        variant="outlined"
                    >
                        {countries.map((country) => (
                            <MenuItem key={country.id} value={country.id}>
                                {country.name}
                            </MenuItem>
                        ))}
                    </Select>

                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">
                    Save
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditHostDialog;

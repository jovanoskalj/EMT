import React, { useState, useEffect } from 'react';
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
import useHosts from "../../../../hooks/useHosts.js";

const Category = {
    ROOM: "ROOM",
    HOUSE: "HOUSE",
    FLAT: "FLAT",
    APARTMENT: "APARTMENT",
    HOTEL: "HOTEL",
    MOTEL: "MOTEL"
};

const EditAccommodationDialog = ({ open, onClose, accommodation, onEdit }) => {
    const { hosts } = useHosts();

    // Кога accommodation ќе се смени, да го ресетирам formData
    const [formData, setFormData] = useState({
        name: accommodation.name,
        category: accommodation.category,
        numRooms: accommodation.numRooms,
        hostId: accommodation.host.id,
    });

    useEffect(() => {
        setFormData({
            name: accommodation.name,
            category: accommodation.category,
            numRooms: accommodation.numRooms,
            hostId: accommodation.host.id,
        });
    }, [accommodation]);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: name === 'hostId' ? Number(value) : value,
        });
    };

    const handleSubmit = () => {
        // Наоѓаме целиот host објект според hostId
        const selectedHost = hosts.find(h => h.id === formData.hostId);
        const updatedData = {
            name: formData.name,
            category: formData.category,
            numRooms: formData.numRooms,
            host: selectedHost,
        };
        onEdit(accommodation.id, updatedData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Accommodation</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />

                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select
                        label="Category"
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                    >
                        {Object.values(Category).map((cat) => (
                            <MenuItem key={cat} value={cat}>
                                {cat.charAt(0).toUpperCase() + cat.slice(1).toLowerCase()}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>

                <TextField
                    margin="dense"
                    label="Number of Rooms"
                    name="numRooms"
                    type="number"
                    value={formData.numRooms}
                    onChange={handleChange}
                    fullWidth
                />

                <FormControl fullWidth margin="dense">
                    <InputLabel>Host</InputLabel>
                    <Select
                        name="hostId"
                        value={formData.hostId}
                        onChange={handleChange}
                        label="Host"
                        variant="outlined"
                    >
                        {hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>
                                {host.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>

            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="warning">
                    Edit
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditAccommodationDialog;

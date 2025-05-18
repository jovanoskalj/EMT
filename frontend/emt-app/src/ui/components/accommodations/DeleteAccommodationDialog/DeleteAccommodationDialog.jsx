import React from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button, Typography } from "@mui/material";

const DeleteAccommodationDialog = ({ open, onClose, accommodation, onDelete }) => {

    const handleDelete = () => {
        onDelete();
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Delete Accommodation</DialogTitle>
            <DialogContent>
                <Typography>
                    Are you sure you want to delete the accommodation <strong>{accommodation?.name || "Unknown"}</strong> hosted by <strong>{accommodation?.host?.name || "Unknown Host"}</strong>?
                </Typography>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose} color="primary">Cancel</Button>
                <Button onClick={handleDelete} color="error" variant="contained">Delete</Button>
            </DialogActions>
        </Dialog>
    );
};

export default DeleteAccommodationDialog;

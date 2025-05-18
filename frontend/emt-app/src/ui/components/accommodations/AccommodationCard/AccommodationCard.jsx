import React, {useState} from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";

import {useNavigate} from "react-router";
import EditAccommodationDialog from "../EditAccommodationDialog/EditAccommodationDialog.jsx";
import DeleteAccommodationDialog from "../DeleteAccommodationDialog/DeleteAccommodationDialog.jsx";


const AccommodationCard = ({accommodation, onEdit, onDelete}) => {
    console.log(accommodation);
    const navigate = useNavigate();
    const [editAccommodationDialogOpen, setEditAccommodationDialogOpen] = useState(false);
    const [deleteAccommodationDialogOpen, setDeleteAccommodationDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{accommodation.name}</Typography>
                    <Typography variant="h5">{accommodation.host.name}</Typography>
                    <Typography
                        variant="body1"
                        fontWeight="bold"
                        sx={{textAlign: "left", fontSize: "1.25rem"}}
                    >
                        {accommodation.category}
                    </Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon />}
                        onClick={() => navigate(`/accommodations/${accommodation.id}`)}
                    >
                        Info
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon />}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditAccommodationDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon />}
                            onClick={() => setDeleteAccommodationDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>

            {editAccommodationDialogOpen && accommodation && (
                <EditAccommodationDialog
                    open={editAccommodationDialogOpen}
                    onClose={() => setEditAccommodationDialogOpen(false)}
                    accommodation={accommodation}
                    onEdit={onEdit}
                />
            )}

            <DeleteAccommodationDialog
                open={deleteAccommodationDialogOpen}
                onClose={() => setDeleteAccommodationDialogOpen(false)}
                accommodation={accommodation}  // прати ја целата accommodation
                onDelete={() => onDelete(accommodation.id)}  // прати го id-то на accommodation за бришење
            />

        </>
    );
};

export default AccommodationCard;

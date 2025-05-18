import React from 'react';
import {useNavigate, useParams} from "react-router";
import {
    Box,
    Button,
    Chip,
    CircularProgress,
    Grid,
    Typography,
    Paper,
    Avatar,
    Stack,
    Rating,
    Breadcrumbs,
    Link
} from "@mui/material";
import {
    ArrowBack,
    Category,
    Factory,
    Star,
    ShoppingCart,
    FavoriteBorder,
    Share
} from "@mui/icons-material";
import useAccommodationDetails from "../../../../hooks/useAccommodationDetails.js";

const AccommodationDetails = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const { accommodation, country } = useAccommodationDetails(id);

    if (!accommodation) {
        return (
            <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh'}}>
                <CircularProgress/>
            </Box>
        );
    }

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/accommodations");
                    }}
                >
                    Accommodations
                </Link>
                <Typography color="text.primary">{accommodation.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Grid container spacing={4}>
                    <Grid size={{xs: 12, md: 9}}>
                        <Box sx={{mb: 3}}>
                            <Typography variant="h5" gutterBottom sx={{fontWeight: 600}}>
                                Accommodation name: {accommodation.name}
                            </Typography>
                            <Typography variant="h5" gutterBottom sx={{fontWeight: 600}}>
                                Number of rooms: {accommodation.numRooms}
                            </Typography>
                            <Typography variant="h5" gutterBottom sx={{fontWeight: 600}}>
                               Category: {accommodation.category}
                            </Typography>
                            <Typography variant="body1" fontWeight="bold">
                                Host: {accommodation.host?.name || 'Unknown'}
                            </Typography>

                        </Box>
                    </Grid>
                    <Grid size={12} display="flex" justifyContent="space-between">
                        <Button
                            variant="outlined"
                            startIcon={<Share/>}
                        >
                            Share
                        </Button>
                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack/>}
                            onClick={() => navigate("/accommodations")}
                        >
                            Back to Accommodations
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default AccommodationDetails;
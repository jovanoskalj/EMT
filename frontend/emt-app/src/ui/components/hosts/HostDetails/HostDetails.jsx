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
import useHostDetails from "../../../../hooks/useHostDetails.js";

const HostDetails = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const { host, country } = useHostDetails(id);

    if (!host) {
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
                        navigate("/hosts");
                    }}
                >
                    Hosts
                </Link>
                <Typography color="text.primary">{host.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Grid container spacing={4}>
                    <Grid size={{xs: 12, md: 9}}>
                        <Box sx={{mb: 3}}>
                            <Typography variant="h5" gutterBottom sx={{fontWeight: 600}}>
                                Host name: {host.name}
                            </Typography>
                            <Typography variant="h5" gutterBottom sx={{fontWeight: 600}}>
                                Host surname: {host.surname}
                            </Typography>
                            <Typography variant="body1" fontWeight="bold">
                                Country: {country?.name || 'Unknown'}
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
                            onClick={() => navigate("/hosts")}
                        >
                            Back to Hosts
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default HostDetails;
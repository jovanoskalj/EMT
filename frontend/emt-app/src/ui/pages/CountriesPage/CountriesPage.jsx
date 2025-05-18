import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import useCountries from "../../../hooks/useCountries.js";
import "./CountriesPage.css";
import AddCountryDialog from "../../components/countries/AddCountryDialog/AddCountryDialog.jsx";
import CountriesGrid from "../../components/countries/CountriesGrid/CountriesGrid.jsx";

const CountriesPage = () => {
    const {countries, loading, onAdd, onEdit, onDelete} = useCountries();
    const [addCountriesDialogOpen, setAddCountriesDialogOpen] = useState(false);

    return (
        <>
            <Box className="countries-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddCountriesDialogOpen(true)}>
                                Add Country
                            </Button>
                        </Box>
                        <CountriesGrid countries={countries} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddCountryDialog
                open={addCountriesDialogOpen}
                onClose={() => setAddCountriesDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default CountriesPage;
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '../services/axios';
import {
    Typography,
    Container,
    Card,
    CardContent,
    CardActionArea,
    Grid,
    CircularProgress,
    Box
} from '@mui/material';

type Restaurant = {
    id: number;
    name: string;
    address?: string;
};

const HomePage = () => {
    const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('/restaurants')
            .then(res => {
                console.log("RESTAURANTE:", res.data);
                if (Array.isArray(res.data)) {
                    setRestaurants(res.data);
                } else {
                    console.error("Răspuns invalid de la server:", res.data);
                }
            })
            .catch(err => {
                console.error("Eroare la încărcarea restaurantelor:", err);
                alert('Eroare la încărcarea restaurantelor');
            })
            .finally(() => setLoading(false));
    }, []);

    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', p: 5 }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Container>
            <Typography variant="h4" gutterBottom>
                Restaurante disponibile
            </Typography>

            {restaurants.length === 0 ? (
                <Typography variant="body1">Nu există restaurante disponibile momentan.</Typography>
            ) : (
                <Grid container spacing={3}>
                    {restaurants.map(restaurant => (
                        <Grid item xs={12} sm={6} md={4} key={restaurant.id}>
                            <Card>
                                <CardActionArea onClick={() => navigate(`/restaurants/${restaurant.id}`)}>
                                    <CardContent>
                                        <Typography variant="h6">{restaurant.name}</Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            {restaurant.address || 'Fără adresă'}
                                        </Typography>
                                    </CardContent>
                                </CardActionArea>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            )}
        </Container>
    );
};

export default HomePage;

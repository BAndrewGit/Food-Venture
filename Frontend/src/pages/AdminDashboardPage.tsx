import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

// MUI
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';

// Axios service
import axios from '../services/axios';

// Chart.js components
import { Bar, Pie, Line } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    ArcElement,
    PointElement,
    LineElement,
    Tooltip,
    Legend
} from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, BarElement, ArcElement, PointElement, LineElement, Tooltip, Legend);

type Product = { id: number; restaurantId: number; };
type Order = { id: number; status: string; createdAt: string; };

export default function AdminDashboardPage() {
    const navigate = useNavigate();
    const [products, setProducts] = useState<Product[]>([]);
    const [orders, setOrders] = useState<Order[]>([]);
    const [restaurants, setRestaurants] = useState<{ id: number; name: string }[]>([]);

    useEffect(() => {
        axios.get('/products').then(res => setProducts(res.data));
        axios.get('/orders/all').then(res => setOrders(res.data));
        axios.get('/restaurants').then(res => setRestaurants(res.data));
    }, []);

    // Bar chart: Produse pe restaurant
    const productCountPerRestaurant = restaurants.map(r => ({
        name: r.name,
        count: products.filter(p => p.restaurantId === r.id).length
    }));

    const barData = {
        labels: productCountPerRestaurant.map(r => r.name),
        datasets: [{ label: 'Număr produse', data: productCountPerRestaurant.map(r => r.count) }]
    };

    // Pie chart: Comenzi pe status
    const statusList = ['NEW', 'DELIVERED', 'CANCELED'];
    const pieData = {
        labels: statusList,
        datasets: [{ data: statusList.map(status => orders.filter(o => o.status === status).length) }]
    };

    // Line chart: Comenzi pe ultimele 7 zile
    const last7days = Array.from({ length: 7 }, (_, i) => {
        const d = new Date();
        d.setDate(d.getDate() - (6 - i));
        return d.toISOString().slice(0, 10);
    });

    const ordersPerDay = last7days.map(date =>
        orders.filter(o => o.createdAt?.slice(0, 10) === date).length
    );

    const lineData = {
        labels: last7days,
        datasets: [{ label: 'Comenzi/zi', data: ordersPerDay, fill: false, tension: 0.2 }]
    };

    return (
        <Container>
            <Typography variant="h4" gutterBottom>
                Admin Dashboard
            </Typography>

            <Box mt={2} mb={4}>
                <Button variant="contained" onClick={() => navigate('/admin/products')}>
                    Manage Products
                </Button>
                <Button variant="outlined" sx={{ ml: 2 }} onClick={() => navigate('/admin/restaurants')}>
                    Manage Restaurants
                </Button>
            </Box>

            <Box
                sx={{
                    display: 'flex',
                    flexWrap: 'wrap',
                    gap: 4,
                    justifyContent: 'space-between'
                }}
            >
                <Box flex="1 1 300px">
                    <Paper sx={{ p: 2 }}>
                        <Typography variant="h6" align="center">Produse pe Restaurant</Typography>
                        <Bar data={barData} />
                    </Paper>
                </Box>

                <Box flex="1 1 300px">
                    <Paper sx={{ p: 2 }}>
                        <Typography variant="h6" align="center">Comenzi pe Status</Typography>
                        <Pie data={pieData} />
                    </Paper>
                </Box>

                <Box flex="1 1 300px">
                    <Paper sx={{ p: 2 }}>
                        <Typography variant="h6" align="center">Comenzi ultimele 7 zile</Typography>
                        <Line data={lineData} />
                    </Paper>
                </Box>
            </Box>
        </Container>
    );
}

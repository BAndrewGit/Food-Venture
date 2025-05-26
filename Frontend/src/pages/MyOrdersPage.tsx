import { useEffect, useState } from 'react';
import axios from '../services/axios';
import {
    Container,
    Typography,
    List,
    ListItem,
    ListItemText,
    Divider,
    Chip,
    Box
} from '@mui/material';

interface Order {
    id: number;
    createdAt: string;
    status: string;
    totalPrice: number;
}

function statusColor(status: string) {
    switch (status) {
        case 'DELIVERED':
            return 'success';
        case 'CANCELED':
            return 'error';
        default:
            return 'warning';
    }
}

export default function MyOrdersPage() {
    const [orders, setOrders] = useState<Order[]>([]);

    useEffect(() => {
        axios.get('/orders')
            .then(res => setOrders(res.data))
            .catch(() => alert('Eroare la încărcarea comenzilor.'));
    }, []);

    return (
        <Container maxWidth="sm">
            <Typography variant="h4" gutterBottom>Comenzile mele</Typography>
            {orders.length === 0 ? (
                <Typography>Nu ai nicio comandă.</Typography>
            ) : (
                <List>
                    {orders.map(order => (
                        <Box key={order.id}>
                            <ListItem>
                                <ListItemText
                                    primary={`Comanda #${order.id}`}
                                    secondary={
                                        <>
                                            {new Date(order.createdAt).toLocaleString()} <br />
                                            <b>{order.totalPrice.toFixed(2)} RON</b>
                                        </>
                                    }
                                />
                                <Chip
                                    label={order.status}
                                    color={statusColor(order.status) as any}
                                    variant="outlined"
                                    sx={{ minWidth: 100, textTransform: 'capitalize' }}
                                />
                            </ListItem>
                            <Divider />
                        </Box>
                    ))}
                </List>
            )}
        </Container>
    );
}

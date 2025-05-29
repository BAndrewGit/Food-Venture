import { useCart } from '../context/CartContext';
import {
    Container,
    Typography,
    IconButton,
    TextField,
    Button,
    List,
    ListItem,
    ListItemText,
    ListItemSecondaryAction,
    Divider,
    Box
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import axios from '../services/axios';

export default function CartPage() {
    const { cartItems, removeFromCart, clearCart, totalPrice, addToCart } = useCart();

    const handleQuantityChange = (productId: number, quantity: number) => {
        if (quantity < 1) return;
        const item = cartItems.find(i => i.productId === productId);
        if (item) {
            const diff = quantity - item.quantity;
            if (diff > 0) {
                addToCart({ ...item, quantity: diff }); // doar adăugăm diferența
            } else {
                // pentru simplificare, ștergem și re-adăugăm
                removeFromCart(productId);
                addToCart({ ...item, quantity });
            }
        }
    };

    const handlePlaceOrder = async () => {
        try {
            await axios.post('/orders', {
                items: cartItems.map(item => ({
                    productId: item.productId,
                    quantity: item.quantity
                }))
            });
            alert('Comanda a fost trimisă cu succes!');
            clearCart();
        } catch {
            alert('Eroare la trimiterea comenzii');
        }
    };

    return (
        <Container>
            <Typography variant="h4" gutterBottom>Coșul tău</Typography>

            {cartItems.length === 0 ? (
                <Typography>Coșul este gol.</Typography>
            ) : (
                <>
                    <List>
                        {cartItems.map(item => (
                            <Box key={item.productId}>
                                <ListItem>
                                    <ListItemText
                                        primary={item.name}
                                        secondary={`${item.price} RON x ${item.quantity} = ${item.price * item.quantity} RON`}
                                    />
                                    <ListItemSecondaryAction>
                                        <Box display="flex" alignItems="center" gap={1}>
                                            <TextField
                                                type="number"
                                                value={item.quantity}
                                                onChange={e => handleQuantityChange(item.productId, parseInt(e.target.value))}
                                                inputProps={{ min: 1 }}
                                                size="small"
                                                sx={{ width: '80px' }}
                                            />
                                            <IconButton onClick={() => removeFromCart(item.productId)}>
                                                <DeleteIcon />
                                            </IconButton>
                                        </Box>
                                    </ListItemSecondaryAction>
                                </ListItem>
                                <Divider />
                            </Box>
                        ))}
                    </List>

                    <Typography variant="h6" mt={2}>Total: {totalPrice.toFixed(2)} RON</Typography>

                    <Box mt={2} display="flex" gap={2}>
                        <Button variant="outlined" onClick={clearCart}>Golește Coșul</Button>
                        <Button variant="contained" color="primary" onClick={handlePlaceOrder}>
                            Trimite Comanda
                        </Button>
                    </Box>
                </>
            )}
        </Container>
    );
}

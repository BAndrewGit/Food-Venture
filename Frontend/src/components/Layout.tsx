import React from 'react';
import { Container, CssBaseline } from '@mui/material';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

const MainLayout = ({ children }: { children: React.ReactNode }) => {
    return (
        <>
            <CssBaseline />
            <Navbar />
            <Container maxWidth="lg" style={{ marginTop: '2rem', minHeight: '80vh' }}>
                {children}
            </Container>
            <Footer />
        </>
    );
};

export default MainLayout;

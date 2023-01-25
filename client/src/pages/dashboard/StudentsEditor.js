import React, { useEffect } from 'react';

import { Typography, List, ListItemButton, ListItemText, Grid } from '@mui/material';
import MainCard from 'components/MainCard';
import { useState } from 'react';
import { useAuthUser } from 'react-auth-kit';
import axios from 'axios';

const StudentsEditor = ({ currentStudent }) => {
    const [student, setStudent] = useState();
    const auth = useAuthUser();

    useEffect(() => {
        if (currentStudent) {
            axios
                .get(`http://localhost:8080/api/students/${currentStudent}`, { headers: { Authorization: `Bearer ${auth().accessToken}` } })
                .then((res) => setStudent(res.data));
        }
    }, [currentStudent]);

    if (!currentStudent) {
        return (
            <>
                <Grid container alignItems="center" justifyContent="space-between">
                    <Grid item>
                        <Typography variant="h5">My profile</Typography>
                    </Grid>
                    <Grid item />
                </Grid>
                <MainCard sx={{ mt: 2 }} content={false}>
                    <List sx={{ p: 0, '& .MuiListItemButton-root': { py: 2 } }}>
                        <ListItemButton divider>
                            <ListItemText primary="Trainer" />
                            <Typography variant="h5">{auth().email}</Typography>
                        </ListItemButton>
                    </List>
                </MainCard>
            </>
        );
    }

    return (
        <>
            <Grid item>
                <Grid container alignItems="center" justifyContent="space-between">
                    <Grid item>
                        <Typography variant="h5">Student</Typography>
                    </Grid>
                </Grid>
                <MainCard sx={{ mt: 2 }} content={false}>
                    <List sx={{ p: 0, '& .MuiListItemButton-root': { py: 2 } }}>
                        <ListItemButton divider>
                            <ListItemText primary="Student Id" />
                            <Typography variant="h5">{student?.username}</Typography>
                        </ListItemButton>
                        <ListItemButton divider>
                            <ListItemText primary="Email" />
                            <Typography variant="h5">{student?.attributes[2].value}</Typography>
                        </ListItemButton>
                        <ListItemButton>
                            <ListItemText primary="Bootcamp" />
                            <Typography variant="h5">{student?.bootcamp}</Typography>
                        </ListItemButton>
                    </List>
                </MainCard>
            </Grid>
            <Grid item>
                <Grid container alignItems="center" justifyContent="space-between">
                    <Grid item>
                        <Typography variant="h5">Subjects</Typography>
                    </Grid>
                </Grid>
                <MainCard sx={{ mt: 2 }} content={false}>
                    <List sx={{ p: 0, '& .MuiListItemButton-root': { py: 2 } }}>
                        {student?.subjects.map((subject) => (
                            <ListItemButton divider>
                                <ListItemText primary={subject.name} />
                                <Typography variant="h5">{subject.score}</Typography>
                            </ListItemButton>
                        ))}
                    </List>
                </MainCard>
            </Grid>
        </>
    );
};

export default StudentsEditor;

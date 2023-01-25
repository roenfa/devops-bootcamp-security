// material-ui
import { Grid, Typography } from '@mui/material';

import { useAuthUser } from 'react-auth-kit';

// project import
import StudentsTable from './StudentsTable';
import StudentsEditor from './StudentsEditor';
import MainCard from 'components/MainCard';
import { useEffect, useState } from 'react';
import TrainersTable from './TrainersTable';

// ==============================|| DASHBOARD - DEFAULT ||============================== //

const DashboardDefault = () => {
    const auth = useAuthUser();
    const [currentStudent, setCurrentStudent] = useState();

    if (auth().roles.includes('TRAINER')) {
        return (
            <Grid container rowSpacing={4.5} columnSpacing={2.75}>
                <Grid item xs={12} md={7} lg={8}>
                    <Grid container alignItems="center" justifyContent="space-between">
                        <Grid item>
                            <Typography variant="h5">Students</Typography>
                        </Grid>
                        <Grid item />
                    </Grid>
                    <MainCard sx={{ mt: 2 }} content={false}>
                        <StudentsTable setCurrentStudent={setCurrentStudent} />
                    </MainCard>
                </Grid>
                <Grid item xs={12} md={5} lg={4}>
                    <StudentsEditor currentStudent={currentStudent} />
                </Grid>
                <Grid item xs={12} md={7} lg={8}>
                    <Grid container alignItems="center" justifyContent="space-between">
                        <Grid item>
                            <Typography variant="h5">Trainers</Typography>
                        </Grid>
                        <Grid item />
                    </Grid>
                    <MainCard sx={{ mt: 2 }} content={false}>
                        <TrainersTable />
                    </MainCard>
                </Grid>
            </Grid>
        );
    }

    return <StudentsEditor currentStudent={auth().username} />;
};

export default DashboardDefault;

import { Routes } from '@angular/router';
import { MainLayoutComponent } from './layout/main-layout/main-layout.component';

export const routes: Routes = [
    {
        path: '', component: MainLayoutComponent, 
        children: [
            { path: '', loadComponent: () => import('./features/voting/voting.page').then(m => m.VotingPage) }
        ]
    }
];

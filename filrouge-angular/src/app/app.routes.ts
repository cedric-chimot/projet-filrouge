import { Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { NotFoundComponent } from './commons/not-found/not-found.component';
import { StagiaireFormulaireComponent } from './components/stagiaire-formulaire/stagiaire-formulaire.component';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';

export const routes: Routes = [
    { path:'', redirectTo: 'home', pathMatch: 'full'},
    { path:'home', component: HomePageComponent},
    { path:'inscription', component: StagiaireFormulaireComponent},
    { path:'dashboard', component: AdminPageComponent},
    { path:'**', component: NotFoundComponent}
];
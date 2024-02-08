import { Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { NotFoundComponent } from './commons/not-found/not-found.component';
import { StagiaireFormulaireComponent } from './components/stagiaire-formulaire/stagiaire-formulaire.component';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';
import { AdminFormationComponent } from './components/admin-formation/admin-formation.component';
import { ContactPageComponent } from './pages/contact-page/contact-page.component';
import { MonProfilPageComponent } from './pages/mon-profil-page/mon-profil-page.component';
import { ProductPageComponent } from './pages/products-page/product-page/product-page.component';
import { ProductsPageComponent } from './pages/products-page/products-page.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { AProposComponent } from './pages/a-propos-page/a-propos.component';

export const routes: Routes = [
    { path:'', redirectTo: 'home', pathMatch: 'full'},
    { path:'home', component: HomePageComponent},
    { path:'inscription', component: StagiaireFormulaireComponent},
    { path:'connexion', component: LoginFormComponent},
    { path:'dashboard', component: AdminPageComponent},
    { path:'dashboard/formations', component: AdminFormationComponent},
    { path:'aPropos', component: AProposComponent},
    { path:'contacter', component: ContactPageComponent },
    { path:'products', component: ProductsPageComponent},
    { path:'products/:type', component: ProductPageComponent},
    { path:'products/:type/:id', component: ProductPageComponent},
    { path:'monProfil', component: MonProfilPageComponent},
    { path:'**', component: NotFoundComponent}
];
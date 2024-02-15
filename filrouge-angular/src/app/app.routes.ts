import { Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { NotFoundComponent } from './commons/not-found/not-found.component';
import { ContactPageComponent } from './pages/contact-page/contact-page.component';
import { MonProfilPageComponent } from './pages/mon-profil-page/mon-profil-page.component';
import { ProductPageComponent } from './pages/products-page/product-page/product-page.component';
import { ProductsPageComponent } from './pages/products-page/products-page.component';
import { AProposComponent } from './pages/a-propos-page/a-propos.component';
import { UserFormulaireComponent } from './pages/inscription-page/inscription-page.component';
import { LoginFormComponent } from './pages/connexion-page/connexion-page.component';
import { AdminFormationComponent } from './pages/admin-page/formation-page/admin-formation-page.component';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';
import { FormateursPageComponent } from './pages/formateurs-page/formateurs-page.component';


export const routes: Routes = [
    { path:'', redirectTo: 'home', pathMatch: 'full'},
    { path:'home', component: HomePageComponent},
    { path:'inscription', component: UserFormulaireComponent},
    { path:'connexion', component: LoginFormComponent},
    { path:'dashboard', component: AdminPageComponent},
    { path:'dashboard/formation', component: AdminFormationComponent},
    { path:'dashboard/formateurs', component: FormateursPageComponent},
    { path:'aPropos', component: AProposComponent},
    { path:'contacter', component: ContactPageComponent },
    { path:'products', component: ProductsPageComponent},
    { path:'products/:type', component: ProductPageComponent},
    { path:'products/:type/:id', component: ProductPageComponent},
    { path:'monProfil', component: MonProfilPageComponent},
    { path:'**', component: NotFoundComponent}
];
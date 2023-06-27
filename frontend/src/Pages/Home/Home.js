import { HeroSection } from "../../components/HeroSection/HeroSection";
import { Showcase } from "../../components/Showcase/Showcase";
import { Testimonials } from "../../components/Testimonials/Testimonails";
import { Location } from "../../components/Location/Location";
import "./Home.css";

export function Home() {
  return (
    <>
      <HeroSection />
      <Showcase />
      <Location />
      <Testimonials />
    </>
  );
}
